import { productModel } from "./productModel";

export interface cartModel{
    id:number,
    userId:number,
    product: productModel,
    quantity:number
}