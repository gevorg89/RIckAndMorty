import SwiftUI
import shared

@main
struct iOSApp: App {
    let rootHolder: RootHolder
    init() {
        PlatformSDK().doInit(configuration: CorePlatformConfiguration())
        rootHolder = RootHolder()
    }
    
    @Environment(\.scenePhase)
        var scenePhase: ScenePhase
    
	var body: some Scene {
		WindowGroup {
            ContentView(rootHolder.root)
                .onChange(of: scenePhase) { newPhase in
                                    switch newPhase {
                                    case .background: LifecycleRegistryExtKt.stop(rootHolder.lifecycle)
                                    case .inactive: LifecycleRegistryExtKt.pause(rootHolder.lifecycle)
                                    case .active: LifecycleRegistryExtKt.resume(rootHolder.lifecycle)
                                    @unknown default: break
                                    }
                                }
		}
	}
}
