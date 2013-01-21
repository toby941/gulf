//
//  MyTableViewCell.h
//  youhao
//
//  Created by toby on 13-1-21.
//  Copyright (c) 2013年 toby. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface MyTableViewCell:UITableViewCell

@property(strong,atomic) IBOutlet UILabel *cellLabel;
@property(strong,atomic) IBOutlet UITextField *cellText;
@end
