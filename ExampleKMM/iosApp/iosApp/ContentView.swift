import SwiftUI
import shared

struct ContentView: View {
    let api = ApiImpl()
    
    @State var greet = "Loading..."
    
    
    func load() {
        print("load()")
        let disposable = api.flowMe(count: 3, succeed: true).subscribe(isThreadLocal: true) {  result in
            print("loaded()")
            self.greet = result.message
        }
//        disposable.dispose()
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
