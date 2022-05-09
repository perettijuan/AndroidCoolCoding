//
//  Helpers.swift
//  iosApp
//
//  Created by Juan Pablo Peretti on 09/05/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Combine
import Foundation
import shared

typealias OnEach<Output> = (Output) -> Void
typealias OnCompletion<Failure> = (Failure?) -> Void

typealias OnCollect<Output, Failure> = (@escaping OnEach<Output>, @escaping OnCompletion<Failure>) -> shared.Cancellable

/**
 Creates a `Publisher` that collects output from a flow wrapper function emitting values from an underlying
 instance of `Flow<T>`.
 */
 // This Just doesn't works because Publishers.Flow does not exists
func collect<Output, Failure>(_ onCollect: @escaping OnCollect<Output, Failure>) -> Publishers.Flow<Output, Failure> {
    return Publishers.Flow(onCollect: onCollect)
}
