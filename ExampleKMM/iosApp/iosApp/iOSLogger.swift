//
//  iOSLogger.swift
//  iosApp
//
//  Created by Juan Pablo Peretti on 11/05/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class iOSLogger : NSObject, Logger {
    func logThread(message: String) {
        print(message)
        print("Current thread \(Thread.current)")
    }
}
