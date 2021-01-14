Pod::Spec.new do |s|
  s.name                = 'MendixViroKit'
  s.version             = '1.0'
  s.summary             = 'Framework containing the Mendix ViroRenderer to be used with Mendix applications. Forked from Viro Media\'s ViroKit.'
  s.source              = { :path => '.' } # source is required, but path will be defined in user's Podfile (this value will be ignored).
  s.vendored_frameworks = 'ViroKit.framework'
  s.homepage            = 'http://mendix.com'
  s.license             = {:type => 'MIT', :text => "" }
  s.author              = 'Mendix'
  s.requires_arc        = true
  s.platform            = :ios, '9.3'

  s.dependency 'React'
end
