//
//  HomeViewController.h
//  youhao
//
//  Created by toby on 13-1-21.
//  Copyright (c) 2013年 toby. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface HomeViewController : UIViewController<UITableViewDelegate, UITableViewDataSource>
{
    NSArray *items;
    
}
@property(strong,atomic)IBOutlet  UINavigationBar *myUINavigationbar;
@property(nonatomic,weak) IBOutlet UITableView *myTableView;
@property (nonatomic,retain) NSArray *items;
@end
