<<<<<<< HEAD
import SupplierRegister from "../pages/RegisterSupplier";
import BuyerRegister from "../pages/RegisterBuyer";
import LandingPage from "../pages/LandingPage";
import { Route, Routes } from "react-router-dom";
import FinancierRegister from "../pages/RegisterFinancier";
import { VerificationPage } from "../pages/VerificationPage";

export const AppRoute = () => {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/register/supplier" element={<SupplierRegister />} />
      <Route path="/register/buyer" element={<BuyerRegister />} />
      <Route path="/register/financier" element={<FinancierRegister />} />
      <Route path="*" element={<LandingPage />} />
      <Route path="/verify" element={<VerificationPage />} />
    </Routes>
  );
};
=======
import { Routes, Route } from 'react-router-dom';
import SupplierRegister from '../pages/RegisterSupplier';
import BuyerRegister from '../pages/RegisterBuyer';
import LandingPage from '../pages/LandingPage';
import SellerLayout from '../layout/seller/SellerLayout';
import SellerDashboard from '../pages/seller/SellerDashboard';
import UploadInvoice from '../pages/seller/UploadInvoice';
import BidsAndOffers from '../pages/seller/BidsAndOffers';
import FinancierRegister from '../pages/RegisterFinancier';

export const AppRoute = () => (
  <Routes>
    <Route path="/" element={<LandingPage />} />
    <Route path="/register/supplier" element={<SupplierRegister />} />
    <Route path="/register/buyer" element={<BuyerRegister />} />
    <Route path="/register/financier" element={<FinancierRegister />} />
    {/* Seller Routes */}
    <Route path="/seller" element={<SellerLayout />}>
      <Route path="dashboard" element={<SellerDashboard />} />
      <Route path="upload" element={<UploadInvoice />} />
      <Route path="bids" element={<BidsAndOffers/>} />
      {/* <Route path="invoices" element={<div>Invoices Page</div>} /> */}
      <Route path="settings" element={<div>Settings Page</div>} />
    </Route>

    <Route path="*" element={<LandingPage />} />
  </Routes>
);
>>>>>>> b8f41825e038ffced6acfd40618779a8690b923a
