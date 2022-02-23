package com.amegane3231.videosearcher.database

import com.amegane3231.videosearcher.data.search.SearchHistory
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.query
import org.koin.core.component.KoinComponent

class SearchHistoryDatabaseImpl : SearchHistoryDatabase, KoinComponent {
    private val realm by lazy {
        val configuration = RealmConfiguration.with(setOf(SearchHistory::class))
        Realm.open(configuration)
    }

    override suspend fun insert(history: SearchHistory) {
        realm.write {
            copyToRealm(history.apply { id = getSearchHistoryId() })
        }
    }

    override suspend fun search(query: String): List<SearchHistory> {
        return realm.query<SearchHistory>("words BEGINSWITH[c]  $0", query).find()
    }

    override suspend fun searchAll(): List<SearchHistory> {
        return realm.query(SearchHistory::class).find()
    }

    override suspend fun delete(history: SearchHistory) {
        realm.write {
            this.delete(history)
        }
    }

    override suspend fun deleteAll() {
        realm.write {
            this.query(SearchHistory::class).find().delete()
        }
    }

    private fun getSearchHistoryId(): Long {
        val maxId = realm.query(SearchHistory::class).find().maxOfOrNull { it.id }
        return if (maxId != null) {
            maxId + 1
        } else {
            0
        }
    }
}
