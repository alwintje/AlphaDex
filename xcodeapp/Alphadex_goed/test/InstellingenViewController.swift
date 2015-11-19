//
//  InstellingenViewController.swift
//  test
//
//  Created by Jari Vdberg on 18/11/15.
//  Copyright © 2015 Jari van den Berg. All rights reserved.
//

import UIKit

class InstellingenViewController: UIViewController {

    @IBOutlet weak var menuToggle: UIBarButtonItem!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if self.revealViewController() != nil {
            menuToggle.target = self.revealViewController()
            menuToggle.action = "revealToggle:"
            self.view.addGestureRecognizer(self.revealViewController().panGestureRecognizer())
        }
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
