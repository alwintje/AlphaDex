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
    
    var visualEffectView: UIVisualEffectView!   // Hierin wordt de 'pointer' naar je visualEffectView opgeslagen.
    // Dit is een "implicitly unwrapped optional".
    
    var image = UIImage()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.detailImageView.image = self.image
        
        visualEffectView = UIVisualEffectView(effect: UIBlurEffect(style: .Dark))
        
        detailImageView.addSubview(visualEffectView)
        
    }
    
    
    override func viewDidLayoutSubviews() {
        // Pas nu heeft detailImageView.bounds de juiste waarden: Auto Layout is klaar.
        visualEffectView.frame = detailImageView.bounds
        view.setNeedsLayout()   // Laat deze regel maar eens weg en kijk wat er gebeurt ;)
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
