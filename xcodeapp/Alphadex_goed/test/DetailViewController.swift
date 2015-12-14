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
    @IBOutlet weak var haarKleur: UILabel!
    @IBOutlet weak var kleurOgen: UILabel!
    @IBOutlet weak var tier: UILabel!
    
    
    var visualEffectView: UIVisualEffectView!   // Hierin wordt de 'pointer' naar je visualEffectView opgeslagen.
    // Dit is een "implicitly unwrapped optional".
    
    var image = UIImage()
    
    var kleurVanOgen = "Bruin"
    var kleurVanHaar = "Blond"
    var tierBenaming = "Legendary"
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        

        self.detailImageView.image = self.image
        
        visualEffectView = UIVisualEffectView(effect: UIBlurEffect(style: .Dark))
        
        detailImageView.addSubview(visualEffectView)
        
        
        let image2 = self.detailImageView.image
        let imageView = UIImageView(image: image2!.rounded)
        
        imageView.frame = CGRect(x: 110, y: 150, width: 150, height: 150)
        view.addSubview(imageView)
        
        
        self.haarKleur.text = kleurVanHaar
        self.kleurOgen.text = kleurVanOgen
        self.tier.text = tierBenaming
        
        
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
