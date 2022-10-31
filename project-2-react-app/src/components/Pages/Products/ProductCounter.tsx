import { current } from "@reduxjs/toolkit";
import { useState } from "react";
import { useAppSelector } from "../../../shared/hooks";
import { selectProduct } from "../Cart/CartSlicer";

function Counter() {

    //need to add quantity to product or cart backend
    //const product = useAppSelector(selectProduct)
    //const quantity = product.quantity
    

    const [currentCount, setCount] = useState(1);
    

    function increment() {
        
        setCount(currentCount + 1)
        console.log(currentCount);
    }

    function decrement() {

        setCount(currentCount - 1)
        console.log(currentCount)
    }


    return <div>
            
            <button onClick={decrement}>-</button><span>{currentCount}</span><button onClick={increment}>+</button>

        </div>
}

export default Counter;