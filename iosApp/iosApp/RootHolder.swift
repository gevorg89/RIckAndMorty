//
//  RootHolder.swift
//  iosApp
//
//  Created by Gevorg Arutyunyan on 24.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class RootHolder : ObservableObject {
    let lifecycle: LifecycleRegistry
    let root: RootComponent

    init() {
        lifecycle = LifecycleRegistryKt.LifecycleRegistry()

        root = DefaultRootComponent(
            componentContext: DefaultComponentContext(lifecycle: lifecycle)
        )

        LifecycleRegistryExtKt.create(lifecycle)
    }

    deinit {
        // Destroy the root component before it is deallocated
        LifecycleRegistryExtKt.destroy(lifecycle)
    }
}
