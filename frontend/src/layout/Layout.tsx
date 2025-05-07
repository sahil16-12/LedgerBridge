import React from 'react';
import Navbar  from './Navbar';
import Footer  from './Footer';

interface LayoutProps {
  children: React.ReactNode;
  showFullMenu?: boolean;
}

export const Layout: React.FC<LayoutProps> = ({ children}) => {

  return (
    <div className="min-h-screen flex flex-col bg-gradient-to-br from-green-50 via-green-100/50 to-white">
      <Navbar />
      <main >
        {children}
      </main>
      <Footer />
    </div>
  );
};
export default Layout;