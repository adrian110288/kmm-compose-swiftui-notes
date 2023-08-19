import SwiftUI
import shared

struct ContentView: View {
    
    private let databaseModule = DatabaseModule()

	var body: some View {
        
        NavigationView {
            NoteListScreen(noteDataSource: databaseModule.noteDataSource)
        }.accentColor(.black)
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
