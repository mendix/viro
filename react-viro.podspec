require 'json'

package = JSON.parse(File.read(File.join(__dir__, '../package.json')))

Pod::Spec.new do |s|
  s.name                = 'ViroReact'
  s.version             = '1.0'
  s.summary             = 'Test description'
  s.source              = { :http => 'https://s3-us-west-2.amazonaws.com/viro-docs/ViroReact.tar.gz'}
  s.source_files        = './ios/dist/include'
  s.public_header_files = './ios/dist/include/*.h', './ios/ViroKit.framework/Headers/*.h'
  s.vendored_libraries  = './ios/dist/static_lib/libViroReact.a'
  spec.vendored_frameworks = './ios/ViroKit.framework'
  s.homepage            = 'http://viromedia.com'
  s.license             = {:type => '', :text => "Mendix" }
  s.author              = 'Mendix, Viro Media'
  s.requires_arc        = true
  s.platform            = :ios, '9.3'

  s.dependency 'React'
end
