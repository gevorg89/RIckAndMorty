import SwiftUI
import shared

struct ContentView: View {
    private let root: RootComponent
    init(_ root: RootComponent) {
            self.root = root
    }

	var body: some View {
		ComposeView(root)
	}
}
