import SwiftUI
import shared

struct ContentView: View {
    let logger = iOSLogger()
    
    @State var greet = "Loading..."
    
    
    func load() {
        let api = ApiImpl(logger: self.logger)
        print("load()")
        logger.logThread(message: "Starting")
        let disposable = api.flowMe(count: 3, succeed: true).subscribe(isThreadLocal: true) {  result in
            logger.logThread(message: "Done")
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
