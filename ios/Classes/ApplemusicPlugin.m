#import "ApplemusicPlugin.h"
#if __has_include(<applemusic/applemusic-Swift.h>)
#import <applemusic/applemusic-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "applemusic-Swift.h"
#endif

@implementation ApplemusicPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftApplemusicPlugin registerWithRegistrar:registrar];
}
@end
