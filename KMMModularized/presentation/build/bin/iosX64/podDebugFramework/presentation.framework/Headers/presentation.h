#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class PresentationDependenciesiOS, PresentationBusinesslogicMessage, PresentationBusinesslogicUseCaseState<__covariant T>, PresentationReaktiveObservableWrapper<__covariant T>, PresentationViewState, PresentationViewStateContent, PresentationKotlinThrowable, PresentationKotlinArray<T>;

@protocol PresentationBusinesslogicMessageUseCase, PresentationInteractor, PresentationPresenter, PresentationUserIntent, PresentationReaktiveObserver, PresentationReaktiveSource, PresentationReaktiveObservable, PresentationReaktiveDisposable, PresentationReaktiveObservableObserver, PresentationReaktiveValueCallback, PresentationReaktiveCompleteCallback, PresentationReaktiveErrorCallback, PresentationReaktiveCompletableCallbacks, PresentationReaktiveObservableCallbacks, PresentationKotlinIterator;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface PresentationBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface PresentationBase (PresentationBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface PresentationMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface PresentationMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorPresentationKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface PresentationNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((swift_name("KotlinByte")))
@interface PresentationByte : PresentationNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface PresentationUByte : PresentationNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface PresentationShort : PresentationNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface PresentationUShort : PresentationNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface PresentationInt : PresentationNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface PresentationUInt : PresentationNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface PresentationLong : PresentationNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface PresentationULong : PresentationNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface PresentationFloat : PresentationNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface PresentationDouble : PresentationNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface PresentationBoolean : PresentationNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DependenciesiOS")))
@interface PresentationDependenciesiOS : PresentationBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)dependenciesiOS __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) PresentationDependenciesiOS *shared __attribute__((swift_name("shared")));
@property (readonly) id<PresentationBusinesslogicMessageUseCase> useCase __attribute__((swift_name("useCase")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Greeting")))
@interface PresentationGreeting : PresentationBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)greeting __attribute__((swift_name("greeting()")));
@end;

__attribute__((swift_name("Interactor")))
@protocol PresentationInteractor
@required
@property (readonly) PresentationReaktiveObservableWrapper<PresentationBusinesslogicUseCaseState<PresentationBusinesslogicMessage *> *> *messageState __attribute__((swift_name("messageState")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("InteractorImpl")))
@interface PresentationInteractorImpl : PresentationBase <PresentationInteractor>
- (instancetype)initWithMessageUC:(id<PresentationBusinesslogicMessageUseCase>)messageUC __attribute__((swift_name("init(messageUC:)"))) __attribute__((objc_designated_initializer));
@property (readonly) PresentationReaktiveObservableWrapper<PresentationBusinesslogicUseCaseState<PresentationBusinesslogicMessage *> *> *messageState __attribute__((swift_name("messageState")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Platform")))
@interface PresentationPlatform : PresentationBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (readonly) NSString *platform __attribute__((swift_name("platform")));
@end;

__attribute__((swift_name("Presenter")))
@protocol PresentationPresenter
@required
- (void)onReady __attribute__((swift_name("onReady()")));
- (void)onUnready __attribute__((swift_name("onUnready()")));
@property (readonly) PresentationReaktiveObservableWrapper<PresentationViewState *> *viewState __attribute__((swift_name("viewState")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PresenterImpl")))
@interface PresentationPresenterImpl : PresentationBase <PresentationPresenter>
- (instancetype)initWithInteractor:(id<PresentationInteractor>)interactor __attribute__((swift_name("init(interactor:)"))) __attribute__((objc_designated_initializer));
- (void)onReady __attribute__((swift_name("onReady()")));
- (void)onUnready __attribute__((swift_name("onUnready()")));
@property (readonly) PresentationReaktiveObservableWrapper<PresentationViewState *> *viewState __attribute__((swift_name("viewState")));
@end;

__attribute__((swift_name("UserIntent")))
@protocol PresentationUserIntent
@required
- (void)onButtonPressed __attribute__((swift_name("onButtonPressed()")));
- (void)onScreenOpen __attribute__((swift_name("onScreenOpen()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserIntentImpl")))
@interface PresentationUserIntentImpl : PresentationBase <PresentationUserIntent>
- (instancetype)initWithUseCase:(id<PresentationBusinesslogicMessageUseCase>)useCase __attribute__((swift_name("init(useCase:)"))) __attribute__((objc_designated_initializer));
- (void)onButtonPressed __attribute__((swift_name("onButtonPressed()")));
- (void)onScreenOpen __attribute__((swift_name("onScreenOpen()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ViewState")))
@interface PresentationViewState : PresentationBase
- (instancetype)initWithLoadingVisible:(BOOL)loadingVisible content:(PresentationViewStateContent *)content __attribute__((swift_name("init(loadingVisible:content:)"))) __attribute__((objc_designated_initializer));
- (BOOL)component1 __attribute__((swift_name("component1()")));
- (PresentationViewStateContent *)component2 __attribute__((swift_name("component2()")));
- (PresentationViewState *)doCopyLoadingVisible:(BOOL)loadingVisible content:(PresentationViewStateContent *)content __attribute__((swift_name("doCopy(loadingVisible:content:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) PresentationViewStateContent *content __attribute__((swift_name("content")));
@property (readonly) BOOL loadingVisible __attribute__((swift_name("loadingVisible")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ViewState.Content")))
@interface PresentationViewStateContent : PresentationBase
- (instancetype)initWithIsVisible:(BOOL)isVisible text:(NSString *)text __attribute__((swift_name("init(isVisible:text:)"))) __attribute__((objc_designated_initializer));
- (BOOL)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (PresentationViewStateContent *)doCopyIsVisible:(BOOL)isVisible text:(NSString *)text __attribute__((swift_name("doCopy(isVisible:text:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL isVisible __attribute__((swift_name("isVisible")));
@property (readonly) NSString *text __attribute__((swift_name("text")));
@end;

__attribute__((swift_name("BusinesslogicMessageUseCase")))
@protocol PresentationBusinesslogicMessageUseCase
@required
- (void)execute __attribute__((swift_name("execute()")));
@property (readonly) PresentationReaktiveObservableWrapper<PresentationBusinesslogicUseCaseState<PresentationBusinesslogicMessage *> *> *state __attribute__((swift_name("state")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BusinesslogicMessage")))
@interface PresentationBusinesslogicMessage : PresentationBase
- (instancetype)initWithText:(NSString *)text __attribute__((swift_name("init(text:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (PresentationBusinesslogicMessage *)doCopyText:(NSString *)text __attribute__((swift_name("doCopy(text:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *text __attribute__((swift_name("text")));
@end;

__attribute__((swift_name("BusinesslogicUseCaseState")))
@interface PresentationBusinesslogicUseCaseState<__covariant T> : PresentationBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("ReaktiveSource")))
@protocol PresentationReaktiveSource
@required
- (void)subscribeObserver:(id<PresentationReaktiveObserver>)observer __attribute__((swift_name("subscribe(observer:)")));
@end;

__attribute__((swift_name("ReaktiveObservable")))
@protocol PresentationReaktiveObservable <PresentationReaktiveSource>
@required
@end;

__attribute__((swift_name("ReaktiveObservableWrapper")))
@interface PresentationReaktiveObservableWrapper<__covariant T> : PresentationBase <PresentationReaktiveObservable>
- (instancetype)initWithInner:(id<PresentationReaktiveObservable>)inner __attribute__((swift_name("init(inner:)"))) __attribute__((objc_designated_initializer));
- (id<PresentationReaktiveDisposable>)subscribe __attribute__((swift_name("subscribe()")));
- (void)subscribeObserver:(id<PresentationReaktiveObservableObserver>)observer __attribute__((swift_name("subscribe(observer:)")));
- (id<PresentationReaktiveDisposable>)subscribeIsThreadLocal:(BOOL)isThreadLocal onSubscribe:(void (^ _Nullable)(id<PresentationReaktiveDisposable>))onSubscribe onError:(void (^ _Nullable)(PresentationKotlinThrowable *))onError onComplete:(void (^ _Nullable)(void))onComplete onNext:(void (^ _Nullable)(T))onNext __attribute__((swift_name("subscribe(isThreadLocal:onSubscribe:onError:onComplete:onNext:)")));
- (id<PresentationReaktiveDisposable>)subscribeIsThreadLocal:(BOOL)isThreadLocal onComplete:(void (^)(void))onComplete onNext:(void (^)(T))onNext __attribute__((swift_name("subscribe(isThreadLocal:onComplete:onNext:)")));
- (id<PresentationReaktiveDisposable>)subscribeIsThreadLocal:(BOOL)isThreadLocal onNext:(void (^)(T))onNext __attribute__((swift_name("subscribe(isThreadLocal:onNext:)")));
- (id<PresentationReaktiveDisposable>)subscribeIsThreadLocal:(BOOL)isThreadLocal onError:(void (^)(PresentationKotlinThrowable *))onError onComplete:(void (^)(void))onComplete onNext:(void (^)(T))onNext __attribute__((swift_name("subscribe(isThreadLocal:onError:onComplete:onNext:)")));
@end;

__attribute__((swift_name("ReaktiveObserver")))
@protocol PresentationReaktiveObserver
@required
- (void)onSubscribeDisposable:(id<PresentationReaktiveDisposable>)disposable __attribute__((swift_name("onSubscribe(disposable:)")));
@end;

__attribute__((swift_name("ReaktiveDisposable")))
@protocol PresentationReaktiveDisposable
@required
- (void)dispose __attribute__((swift_name("dispose()")));
@property (readonly) BOOL isDisposed __attribute__((swift_name("isDisposed")));
@end;

__attribute__((swift_name("ReaktiveValueCallback")))
@protocol PresentationReaktiveValueCallback
@required
- (void)onNextValue:(id _Nullable)value __attribute__((swift_name("onNext(value:)")));
@end;

__attribute__((swift_name("ReaktiveCompleteCallback")))
@protocol PresentationReaktiveCompleteCallback
@required
- (void)onComplete __attribute__((swift_name("onComplete()")));
@end;

__attribute__((swift_name("ReaktiveErrorCallback")))
@protocol PresentationReaktiveErrorCallback
@required
- (void)onErrorError:(PresentationKotlinThrowable *)error __attribute__((swift_name("onError(error:)")));
@end;

__attribute__((swift_name("ReaktiveCompletableCallbacks")))
@protocol PresentationReaktiveCompletableCallbacks <PresentationReaktiveCompleteCallback, PresentationReaktiveErrorCallback>
@required
@end;

__attribute__((swift_name("ReaktiveObservableCallbacks")))
@protocol PresentationReaktiveObservableCallbacks <PresentationReaktiveValueCallback, PresentationReaktiveCompletableCallbacks>
@required
@end;

__attribute__((swift_name("ReaktiveObservableObserver")))
@protocol PresentationReaktiveObservableObserver <PresentationReaktiveObserver, PresentationReaktiveObservableCallbacks>
@required
@end;

__attribute__((swift_name("KotlinThrowable")))
@interface PresentationKotlinThrowable : PresentationBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(PresentationKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(PresentationKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (PresentationKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) PresentationKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface PresentationKotlinArray<T> : PresentationBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(PresentationInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<PresentationKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol PresentationKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
