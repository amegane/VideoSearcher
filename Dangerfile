github.dismiss_out_of_range_messages

warn("PR is classed as Work in Progress") if github.pr_title.include? "[WIP]"

# ktlint
ktlint_report_dir = 'build/reports/ktlint/**/*.xml'
Dir[ktlint_report_dir].each do |file_name|
    checkstyle_format.base_path = Dir.pwd
    checkstyle_format.report file_name
end