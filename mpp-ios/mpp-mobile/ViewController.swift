//
//  ViewController.swift
//  mpp-mobile
//
//  Created by Александр Дунаев on 17.04.2020.
//  Copyright © 2020 swampfeast. All rights reserved.
//

import UIKit
import MppShared

class ViewController: UIViewController {
        
    private var viewModel: BreedViewModel = Injection.resolve(BreedViewModel.self)!
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    
    fileprivate var items = [Breed]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.tableFooterView = UIView()
        tableView.dataSource = self
        tableView.delegate = self
        viewModel.data.observe(block: {[weak self] data in
            if let state = data as? BreedViewState {
                self?.show(state: state)
            }
        })
    }
    
    private func show(state: BreedViewState) {
        switch (state){
        case is BreedViewState.Error:
            activityIndicator.stopAnimating()
            showError()
        case is BreedViewState.Loading:
            activityIndicator.startAnimating()
        case is BreedViewState.Success:
            if let state = state as? BreedViewState.Success {
                updateData(items: state.value)
            }
            activityIndicator.stopAnimating()
        default:
            break
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        if isMovingFromParent && isBeingDismissed {
            viewModel.onCleared()
        }
    }
    
    private func showError() {
        let alert = UIAlertController(title: "Error", message: nil, preferredStyle: .alert)
        let action = UIAlertAction(title: "Repeat", style: .default, handler: {
            [unowned self] alert in
            self.viewModel.load()
        })
        alert.addAction(action)
        present(alert, animated: true, completion: nil)
    }
    
    private func updateData(items: [Breed]) {
        self.items = items
        tableView.reloadData()
    }

}

extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        cell.textLabel?.text = items[indexPath.item].name
        return cell
    }
}

extension ViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let breed = items[indexPath.item]
        viewModel.onBreedClick(breed: breed)
    }
}
