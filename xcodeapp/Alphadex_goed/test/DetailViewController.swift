//
//  DetailViewController.swift
//  test
//
//  Created by Jari Vdberg on 17/11/15.
//  Copyright © 2015 Jari van den Berg. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var detailImageView: UIImageView!

    
    var image = UIImage()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

       self.detailImageView.image = self.image
        
        let visualEffectView = UIVisualEffectView(effect: UIBlurEffect(style: .Dark)) as UIVisualEffectView
        
        visualEffectView.frame = detailImageView.bounds
        
        detailImageView.addSubview(visualEffectView)
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
