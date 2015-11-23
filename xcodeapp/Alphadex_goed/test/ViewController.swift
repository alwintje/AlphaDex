//
//  ViewController.swift
//  test
//
//  Created by Jari Vdberg on 17/11/15.
//  Copyright © 2015 Jari van den Berg. All rights reserved.
//

import UIKit

extension UIImage {
    var rounded: UIImage {
        let imageView = UIImageView(image: self)
        imageView.layer.cornerRadius = size.height < size.width ? size.height/2 : size.width/2
        imageView.layer.masksToBounds = true
        UIGraphicsBeginImageContext(imageView.bounds.size)
        imageView.layer.renderInContext(UIGraphicsGetCurrentContext()!)
        let result = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        return result
    }
}

class ViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource {
    
    @IBOutlet weak var menuToggle: UIBarButtonItem!
 
    @IBOutlet weak var collectionView: UICollectionView!
    
    var tableData = [
        "Rowie", "Manon", "Sacha", "Rowie", "Manon", "Sacha", "Rowie", "Manon", "Sacha"
    ]
    var tableImage = [
        UIImage(named: "Rowie.jpg"), UIImage(named: "Manon.jpg"), UIImage(named: "Sacha.jpg"),
        UIImage(named: "Rowie.jpg"), UIImage(named: "Manon.jpg"), UIImage(named: "Sacha.jpg"),
        UIImage(named: "Rowie.jpg"), UIImage(named: "Manon.jpg"), UIImage(named: "Sacha.jpg")
    ]
    
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        if self.revealViewController() != nil {
            menuToggle.target = self.revealViewController()
            menuToggle.action = "revealToggle:"
            self.view.addGestureRecognizer(self.revealViewController().panGestureRecognizer())
        }
        
    }
    
    
    func collectionView(collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.tableData.count
    }
    
    func collectionView(collectionView: UICollectionView, cellForItemAtIndexPath indexPath: NSIndexPath) -> UICollectionViewCell {
        let cell: CollectionvwCell = collectionView.dequeueReusableCellWithReuseIdentifier("Cell", forIndexPath: indexPath) as! CollectionvwCell
        cell.labelCell?.text = self.tableData[indexPath.row]
        cell.imgCell?.image  = self.tableImage[indexPath.row]?.rounded
        return cell
    }
    
    func collectionView(collectionView: UICollectionView, didSelectItemAtIndexPath indexPath: NSIndexPath) {
        self.performSegueWithIdentifier("showImage", sender: self)
    }
    

    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "showImage"
        {
            let indexPaths = self.collectionView!.indexPathsForSelectedItems()!
            let indexPath = indexPaths[0] as NSIndexPath
            
            let vc = segue.destinationViewController as! DetailViewController
            
            vc.image = self.tableImage[indexPath.row]!
            vc.title = self.tableData[indexPath.row]
            
            
        }
    }
    
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

