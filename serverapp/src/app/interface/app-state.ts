import { DataState } from "../enum/data-state.enum";

export interface AppState<T> {
    dataState: DataState;
    appState: T; //generic type
    error: string;
}