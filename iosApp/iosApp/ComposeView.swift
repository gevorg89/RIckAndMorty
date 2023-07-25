//
//  ComposeView.swift
//  iosApp
//
//  Created by Gevorg Arutyunyan on 10.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable{
    private let root: RootComponent
    init(_ root: RootComponent) {
            self.root = root
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
    }

    func makeUIViewController(context: Context) -> some UIViewController {
        IosAppKt.MainViewController(root: root)
    }
}
