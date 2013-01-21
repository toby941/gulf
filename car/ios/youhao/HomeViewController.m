//
//  HomeViewController.m
//  youhao
//
//  Created by toby on 13-1-21.
//  Copyright (c) 2013年 toby. All rights reserved.
//

#import "HomeViewController.h"
#import "MyTableViewCell.h"
@interface HomeViewController ()



@end

@implementation HomeViewController

@synthesize myUINavigationbar;
@synthesize myTableView;
@synthesize items;


- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.items = [[NSArray alloc] initWithObjects:@"Item 1",@"Item 2",@"Item 3",nil];
    myTableView.delegate=self;
    
    myTableView.dataSource=self;
   
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
    if(cell == nil) cell = [[MyTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:@"cell"];
    
    // Add a detail view accessory
    // cell.accessoryType = UITableViewCellAccessoryDetailDisclosureButton;
    cell.cellLabel.text=@"label";
    
    
    UIImage *image = [UIImage imageNamed:@"second.png"];
    cell.imageView.image = image;
    UIImage *highLighedImage = [UIImage imageNamed:@"first.png"];
    cell.imageView.highlightedImage = highLighedImage;
    
    
    // Step 3: Set the cell text
    //  cell.textLabel.text = [items objectAtIndex:indexPath.row];
    //  cell.dec=@"test";
    // Step 4: Return the cell
    return cell;
}
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

@end
