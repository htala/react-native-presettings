//
//  RNPreSettings.h
//  HTALA
//
//  Created by H.Talaiee on 25/08/2018.
//  Copyright © 2018 HTALA. All rights reserved.
//


#import <UIKit/UIKit.h>
#import <sys/utsname.h>

#if __has_include(<React/RCTAssert.h>)
#import <React/RCTBridgeModule.h>
#else
#import "RCTBridgeModule.h"
#endif

@interface RNDeviceInfo : NSObject <RCTBridgeModule>

@end
