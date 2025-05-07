import React, {
  createContext,
  useState,
  useContext,
  ReactNode,
} from "react";

interface UserContextType {
  user: any | null;
  role: string | null;
  auth: boolean;
  setUser: (user: any) => void;
  setRole: (role: string) => void;
  setAuth: (auth: boolean) => void;    
  logout: () => void;
}

const UserContext = createContext<UserContextType | undefined>(undefined);

export const useUserContext = () => {
  const ctx = useContext(UserContext);
  if (!ctx) throw new Error("useUserContext must be used within UserProvider");
  return ctx;
};

export const UserProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<any | null>(null);
  const [role, setRole] = useState<string | null>(null);
  const [auth, setAuth] = useState<boolean>(false);      // ← auth state

  const logout = () => {
    setUser(null);
    setRole(null);
    setAuth(false);
    localStorage.clear();   // optional
  };

  return (
    <UserContext.Provider
      value={{ user, role, auth, setUser, setRole, setAuth, logout }}
    >
      {children}
    </UserContext.Provider>
  );
};
