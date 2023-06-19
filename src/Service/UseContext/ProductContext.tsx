import { createContext, useState } from "react";

export interface UseContextType {
    product: any,
    setProduct: any
}

interface ProductDetails {
    id:number
}


interface ProductContextProviderType {
    children: React.ReactNode
}

export const ProductContext = createContext({} as UseContextType);

export const ProductContextProvider = ({ children }: ProductContextProviderType) => {
    const [product, setProduct] = useState<ProductDetails | null>()
    return (
        <ProductContext.Provider value={{ product, setProduct }}>
            {children}
        </ProductContext.Provider>)

}