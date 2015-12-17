//
//  InstellingenViewController.swift
//  test
//
//  Created by Jari Vdberg on 14/12/15.
//  Copyright © 2015 Jari van den Berg. All rights reserved.
//

import UIKit

class InstellingenViewController: UIViewController {

    @IBOutlet weak var menuToggle: UIBarButtonItem!
    @IBOutlet weak var usernameField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    
    @IBAction func loginAction(sender: AnyObject) {
        
        var username = self.usernameField.text
        var password = self.passwordField.text
        
        
        let correctUsername = "Jari"
        let correctPassword = "1"
        
        // Validate the text fields
        if username == correctUsername && password == correctPassword {
        } else {
            let alert = UIAlertView()
            alert.title = "Alert"
            alert.message = "Username of Wachtwoord fout!"
            alert.addButtonWithTitle("Ok!")
            alert.show()
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if self.revealViewController() != nil {
            menuToggle.target = self.revealViewController()
            menuToggle.action = "revealToggle:"
            self.view.addGestureRecognizer(self.revealViewController().panGestureRecognizer())
        }
        // Do any additional setup after loading the view.
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
