import { createSlice, PayloadAction, Slice } from "@reduxjs/toolkit";
import { RootState } from "../../shared/store";
import { IUserModel } from "../models/userModel";


const initialState:IUserModel = {
    id:0,
    firstName:"",
    lastName:"",
    email:"",
    role:0,
};

const userSlice:Slice<IUserModel> = createSlice({
    name:"user",
    initialState,

    reducers: {
        setUser: ( state, action:PayloadAction<IUserModel> ) => {

            console.log(action.payload)

            state.id = action.payload.id;
            state.firstName = action.payload.firstName;
            state.lastName = action.payload.lastName;
            state.email = action.payload.email;
            state.role = action.payload.role;
        },

        setDefault: ( state ) => {

            state.id = 0;
            state.firstName = "";
            state.lastName = "";
            state.email = "";
            state.role = 0;
        },

    },


});

export const {setUser, setDefault} = userSlice.actions;

export default userSlice.reducer;

export const selectUser = (state:RootState) => state.user;