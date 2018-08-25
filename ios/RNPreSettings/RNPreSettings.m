//
//  RNPreSettings.m
//  HTALA
//
//  Created by H.Talaiee on 25/08/2018.
//  Copyright © 2018 HTALA. All rights reserved.
//


#define  DATA_FILE_NAME "HT_RNPreSettings"

#import "RNPreSettings.h"

@interface RNPreSettings()

@end

@implementation RNPreSettings


RCT_EXPORT_MODULE(RNPreSettings)

+ (BOOL)requiresMainQueueSetup
{
   return YES;
}


- (NSString*) loadSettings
{
    NSString *path = [[NSBundle mainBundle] pathForResource:@DATA_FILE_NAME ofType:@"txt"];
    return [NSString stringWithContentsOfFile:path encoding:NSUTF8StringEncoding error:nil]; 
}

RCT_EXPORT_METHOD(setSettings:(NSString*)settings)
{
    NSString *path = [[NSBundle mainBundle] pathForResource:@DATA_FILE_NAME ofType:@"txt"];
    [settings writeToFile:path atomically:NO encoding:NSUTF8StringEncoding error:nil];
}

- (NSDictionary *)constantsToExport
{
    NSString *settings = [self loadSettings];
    return @{
             @"settings": settings
	};
}


@end
