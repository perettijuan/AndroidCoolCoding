import SwiftUI
import shared

struct ContentView: View {
    
    let dataSource = MessageDataSourceImpl()
    let repository: MessageRepository
    let presenter: Presenter
    let userIntent: UserIntent
    let disposable: ReaktiveDisposable
    
    
    init() {
        repository = MessageRepositoryImpl(dataSource: self.dataSource)
        presenter = PresenterImpl(repository: self.repository)
        userIntent = UserIntentImpl(repository: self.repository)
        
        disposable = self.presenter.uiState.subscribe(isThreadLocal: true) { vstate in
            if (vstate.loadingVisible) {
                print("Loading ")
            }
            
            if (vstate.content.isVisible) {
                print("Content visible")
                print(vstate.content.text)
            }
        }
    }
    
    
	
	var body: some View {
        Button("Button title") {
            // Note: the button will not hide because of my lack of knowledge
            // about SwiftUi
            self.userIntent.onButtonPressed()
        }.onAppear() {
            self.start()
        }
    }
    
    func start() {
        self.presenter.onReady()
    }
    
    func clear() {
        self.presenter.onUnready()
        self.disposable.dispose()
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
	}
}
