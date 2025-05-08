// UserContext.tsx
import React, { createContext, useState, useContext, ReactNode } from "react";

interface UserContextType {
  user: any | null;
  role: string | null;
  auth: boolean;
  setUser: (user: any) => void;
  setRole: (role: string) => void;
  setAuth: (auth: boolean) => false;
  logout: () => void; // Add logout to the context type
}

const UserContext = createContext<UserContextType | undefined>(undefined);

export const useUserContext = () => {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error("useUserContext must be used within a UserProvider");
  }
  return context;
};

export const UserProvider: React.FC<{ children: ReactNode }> = ({
  children,
}) => {
  const [user, setUser] = useState<any | null>(null);
  const [role, setRole] = useState<string | null>(null);

  const logout = () => {
    // Clear user and role state
    setUser(null);
    setRole(null);

    // // Optionally, remove the token from localStorage or sessionStorage
    // localStorage.removeItem("authToken"); // If you store your token in localStorage
    // sessionStorage.removeItem("authToken"); // If you store your token in sessionStorage
    // Any other necessary cleanup logic (e.g., redirect user to login page)
  };
  return (
    <UserContext.Provider value={{ user, role, setUser, setRole, logout }}>
      {children}
    </UserContext.Provider>
  );
};
