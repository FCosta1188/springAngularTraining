export enum DataState { // without the string values, TS is gonna assign indexes to each item, and show them as values when calling the enum
    LOADING_STATE = 'LOADING_STATE', //0
    LOADED_STATE = 'LOADED_STATE', //1
    ERROR_STATE = 'ERROR_STATE' //2
}