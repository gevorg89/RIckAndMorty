import SwiftUI
//import shared

struct ContentView: View {
    private let root: DefaultRootComponent
    init(_ root: RootComponent) {
            self.root = root
    }

	var body: some View {
		ComposeView()
	}
}
