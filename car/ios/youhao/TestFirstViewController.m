//
//  TestFirstViewController.m
//  youhao
//
//  Created by toby on 13-1-11.
//  Copyright (c) 2013年 toby. All rights reserved.
//

#import "TestFirstViewController.h"
#import "MyTableViewCell.h"
@interface TestFirstViewController ()



@end

@implementation TestFirstViewController

@synthesize mydateText=_mydateText;
@synthesize mySoliNo=_mySoliNo;

@synthesize myTableView;
@synthesize items; 
- (void)viewDidLoad
{
    [super viewDidLoad];
	 self.items = [[NSArray alloc] initWithObjects:@"Item 1",@"Item 2",@"Item 3",nil];
    myTableView.delegate=self;
    
    UIView *headerView = [[UIView alloc] initWithFrame: CGRectMake(0, 0, 320, 44)];
    self.tableView.tableHeaderView = headerView;
   
    //加了上面的代码之后tabview就向下移了44个像素，那刚好放上navbar
    CGRect aScreenRect = [[UIScreen mainScreen] bounds];
    
    //创建navbar
    UINavigationBar *nav = [[UINavigationBar alloc] initWithFrame:CGRectMake(0, 0, aScreenRect.size.width, 44)];
    //创建navbaritem
    UINavigationItem *NavTitle = [[UINavigationItem alloc] initWithTitle:@"详细介绍"];
    
    [nav pushNavigationItem:NavTitle animated:YES];
    
    [self.view addSubview:nav];
    
    //创建barbutton 创建系统样式的
    UIBarButtonItem *item = [[UIBarButtonItem alloc]initWithBarButtonSystemItem:UIBarButtonSystemItemReply target:self action:@selector(navBackBt:)];
    
    //设置barbutton
    NavTitle.leftBarButtonItem = item;
    [nav setItems:[NSArray arrayWithObject:NavTitle]];
    
   
    
   }



- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [self.items count]; // or self.items.count;
}

- (MyTableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CustomCellIdentifier = @"mycell";
    static BOOL nibsRegistered = NO;
    if (!nibsRegistered) {
        UINib *nib = [UINib nibWithNibName:@"MyTableViewCell" bundle:nil];
        [tableView registerNib:nib forCellReuseIdentifier:CustomCellIdentifier];
        nibsRegistered = YES;
    }
    
    
    // Step 1: Check to see if we can reuse a cell from a row that has just rolled off the screen
    MyTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CustomCellIdentifier];
    
    // Step 2: If there are no cells to reuse, create a new one
    if(cell == nil) cell = [[MyTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"cell"];
    
    // Add a detail view accessory
   // cell.accessoryType = UITableViewCellAccessoryDetailDisclosureButton;
    cell.cellText.text=@"label";
    
    // Step 3: Set the cell text
  //  cell.textLabel.text = [items objectAtIndex:indexPath.row];
  //  cell.dec=@"test";
    // Step 4: Return the cell
    return cell;
}
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}






- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
