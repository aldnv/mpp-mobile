//
//  Injection.swift
//  mpp-mobile
//
//  Created by Александр Дунаев on 22.04.2020.
//  Copyright © 2020 swampfeast. All rights reserved.
//

import Swinject
import MppShared

class Injection {
    private static let container: Container = {
        let container = Container()
        
        container.register(BreedsApi.self){ _ in
            BreedsApi()
        }
        container.register(BreedsDataSource.self){ r in
            BreedsDataSource(breedsApi: r.resolve(BreedsApi.self)!)
        }
        container.register(BreedRepository.self){ r in
            BreedRepositoryImpl(breedsDataSource: r.resolve(BreedsDataSource.self)!)
        }
        container.register(GetBreeds.self){ r in
            GetBreeds(repository: r.resolve(BreedRepository.self)!)
            
        }.inObjectScope(.container)
        container.register(Navigator.self, factory: { _ in
            BreedsNavigator()
        })
        container.register(BreedViewModel.self, factory: {r in
            BreedViewModel(getBreeds: r.resolve(GetBreeds.self)!, navigator: r.resolve(Navigator.self)!)
        })
        
        return container
    }()
    
    static func resolve<Service>(_ serviceType: Service.Type) -> Service? {
        return Injection.container.resolve(serviceType)
    }
}
