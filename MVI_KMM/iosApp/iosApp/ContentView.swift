import SwiftUI
import shared

struct ContentView: View {
    
    let dataSource = MessageDataSourceImpl()
    let repository: MessageRepository
    let presenter: Presenter
    let userIntent: UserIntent
    let disposable: ReaktiveDisposable
    let lifeycle: MviLifecycleiOs
    
    
    init() {
        lifeycle = MviLifecycleiOs()
        repository = MessageRepositoryImpl(dataSource: self.dataSource)
        presenter = PresenterImpl(repository: self.repository, lifecycle: lifeycle)
        userIntent = UserIntentImpl(repository: self.repository, lifecycle: lifeycle)
        
        disposable = self.presenter.uiState.subscribe(isThreadLocal: true) { vstate in
            if (vstate.loadingVisible) {
                print("Loading ")
            }
            
            if (vstate.content.isVisible) {
                print("Content visible")
                print(vstate.content.text)
            }
        }
    
        lifeycle.onCreated()
    }
	
	var body: some View {
        Button("Button title") {
            // Note: the button will not hide because of my lack of knowledge
            // about SwiftUi
            self.userIntent.onButtonPressed()
        }.onAppear() {
            self.lifeycle.onAppear()
        }.onDisappear() {
            self.lifeycle.onDisappear()
            self.disposable.dispose()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
	}
}
