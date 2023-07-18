//
//  ComposeView.swift
//  iosApp
//
//  Created by Gevorg Arutyunyan on 10.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import common.umbrella


struct ComposeView: UIViewControllerRepresentable{
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
    }

    func makeUIViewController(context: Context) -> some UIViewController {
        AppKt.MainViewController()
        //UITextView(frame: CGRect(x: 10, y: 20, width: 100, height: 100))
    }
}
