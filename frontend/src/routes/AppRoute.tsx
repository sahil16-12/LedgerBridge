import SupplierRegister from '../pages/RegisterSupplier'
import BuyerRegister from '../pages/RegisterBuyer'
import LandingPage from '../pages/LandingPage'
import { Route, Routes } from 'react-router-dom'
import FinancierRegister from '../pages/RegisterFinancier'


export const AppRoute = () => {
    return (
        <Routes>
            <Route path="/" element={<LandingPage />} />
            <Route path="/register/supplier" element={<SupplierRegister />} />
            <Route path="/register/buyer" element={<BuyerRegister />} />
            <Route path="/register/financier" element={<FinancierRegister />} />
            <Route path="*" element={<LandingPage />} />

        </Routes>
    )
  }