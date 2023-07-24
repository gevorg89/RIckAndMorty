import SwiftUI
import shared

@main
struct iOSApp: App {
    let rootHolder: RootHolder = RootHolder()
//    @UIApplicationDelegateAdaptor(AppDelegate.self)
//        var appDelegate: AppDelegate
    
	var body: some Scene {
		WindowGroup {
            ContentView(rootHolder.root)
//                .onChange(of: scenePhase) { newPhase in
//                                    switch newPhase {
//                                    case .background: LifecycleRegistryExtKt.stop(rootHolder.lifecycle)
//                                    case .inactive: LifecycleRegistryExtKt.pause(rootHolder.lifecycle)
//                                    case .active: LifecycleRegistryExtKt.resume(rootHolder.lifecycle)
//                                    @unknown default: break
//                                    }
//                                }
		}
	}
}
