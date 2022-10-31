import { createSlice, PayloadAction, Slice } from "@reduxjs/toolkit";
import { RootState } from "../../../shared/store";
import { productModel } from "../../models/productModel";



const initialState:productModel = {
    avg_rating: 0,
    brand: "",
    description: "",
    id: 0,
    imagePath: "",
    price: 0,
    name: "",  
    weight: 0,
};

const cartSlice:Slice<productModel> = createSlice({
    name:"product",
    initialState,

    reducers: {
        setProduct: ( state, action:PayloadAction<productModel> ) => {

            console.log(action.payload)

            state.avg_rating = action.payload.avg_rating;
            state.brand = action.payload.brand;
            state.description = action.payload.description;
            state.id = action.payload.id;
            state.imagePath = action.payload.imagePath;
            state.price = action.payload.price;
            state.name = action.payload.name;
            state.weight = action.payload.weight;
        },

        setDefault: ( state ) => {

            state.avg_rating = 0;
            state.brand = "";
            state.description = "";
            state.id = 0;
            state.imagePath = "";
            state.price = 0;
            state.name = "";  
            state.weight = 0;
        },

    },


});

export const {setProduct, setDefault} = cartSlice.actions;

export default cartSlice.reducer;

export const selectProduct = (state:RootState) => state.product;