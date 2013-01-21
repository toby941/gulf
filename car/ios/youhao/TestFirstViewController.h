//
//  TestFirstViewController.h
//  youhao
//
//  Created by toby on 13-1-11.
//  Copyright (c) 2013年 toby. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TestFirstViewController : UITableViewController<UITableViewDelegate, UITableViewDataSource>
{
NSArray *items;
    
}

@property (nonatomic,retain) NSArray *items;
@property(nonatomic,weak) IBOutlet UITableView *myTableView;

@property(nonatomic,retain)IBOutlet  UITextField *mydateText;
@property(nonatomic,retain)IBOutlet UITextField *mySoliNo;

@end
