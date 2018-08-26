//
//  RNPreSettings.m
//  HTALA
//
//  Created by H.Talaiee on 25/08/2018.
//  Copyright © 2018 HTALA. All rights reserved.
//


#define  DATA_FILE_NAME "HT_RNPreSettings.txt"

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
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentsDirectory = [paths objectAtIndex:0];
    NSString *path = [documentsDirectory stringByAppendingPathComponent:@DATA_FILE_NAME];
    return [NSString stringWithContentsOfFile:path encoding:NSUTF8StringEncoding error:nil]; 
}

RCT_EXPORT_METHOD(setSettings:(NSString*)settings)
{
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentsDirectory = [paths objectAtIndex:0];
    NSString *path = [documentsDirectory stringByAppendingPathComponent:@DATA_FILE_NAME];
    [settings writeToFile:path atomically:YES encoding:NSUTF8StringEncoding error:nil];
}

- (NSDictionary *)constantsToExport
{
    NSString *settings = [self loadSettings];
    return @{
             @"settings": settings ?: [NSNull null]
	};
}


@end
