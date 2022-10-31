import { configureStore } from "@reduxjs/toolkit";
import UserSlicer from "../components/Login/UserSlicer";
import CartSlicer from "../components/Pages/Cart/CartSlicer";

export const store = configureStore({
    reducer: {
        user: UserSlicer,
        product: CartSlicer,
    }
})

export type AppDispatch = typeof store.dispatch
export type RootState = ReturnType<typeof store.getState>