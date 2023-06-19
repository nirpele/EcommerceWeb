import { createContext, useState } from "react";

export interface UseContextType {
    user: any,
    setUser: any
}

interface AutaUser {
    email: string,
    userType: string,
    password: string,
    id:number,
    token:string
}


interface UserContextProviderType {
    children: React.ReactNode
}

export const UserContext = createContext({} as UseContextType);

export const UserContextProvider = ({ children }: UserContextProviderType) => {
    const [user, setUser] = useState<AutaUser | null>()
    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>)

}

