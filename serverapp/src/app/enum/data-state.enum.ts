export enum DataState { // without the string values, TS is gonna assign indexes to each item, and show them as values when calling the enum
    LOADING = 'LOADING', //0
    LOADED = 'LOADED', //1
    ERROR = 'ERROR' //2
}