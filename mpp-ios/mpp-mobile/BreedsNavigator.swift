//
//  BreedsNavigator.swift
//  mpp-mobile
//
//  Created by Александр Дунаев on 23.04.2020.
//  Copyright © 2020 swampfeast. All rights reserved.
//

import UIKit
import MppShared

class BreedsNavigator: Navigator{
    func openImageList(breed: Breed) {
        guard let window = UIApplication.shared.windows.first else {return}
        let alertDialog = UIAlertController(title: "not implemented", message: nil, preferredStyle: .alert)
        let action = UIAlertAction(title: "OK", style: .default, handler: nil)
        alertDialog.addAction(action)
        window.rootViewController?.present(alertDialog, animated: true, completion: nil)
    }
}
