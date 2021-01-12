require 'json'

package = JSON.parse(File.read(File.join(__dir__, '../package.json')))

Pod::Spec.new do |s|
  s.name                = 'ViroReact'
  s.version             = '1.0'
  s.summary             = 'Test description'
  s.source              = { :path => '.'}
  s.source_files        = '../include'
  s.public_header_files = '../include/*.h'
  s.vendored_libraries  = 'libViroReact.a'
  s.homepage            = 'http://viromedia.com'
  s.license             = {:type => 'MIT', :text => "" }
  s.author              = 'Mendix forked from Viro Media'
  s.requires_arc        = true
  s.platform            = :ios, '9.3'

  s.dependency 'React'
end
