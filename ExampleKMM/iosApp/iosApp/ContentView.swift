import SwiftUI
import shared

struct ContentView: View {
    let api = Api()
    
    @State var greet = "Loading..."
    
    
    func load() {
        api.state.watch { apiResult in
            if let result = apiResult {
                self.greet = result.message
            }
        }
        
        api.flowMe(count: 3, succeed: true) {_,_ in 
            
        }
    }

	var body: some View {
        
        
        Text(greet).onAppear() {
            load()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
