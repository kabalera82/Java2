flowchart TB
subgraph DAFO["Análisis DAFO - NovaPay TPV"]
subgraph F["Fortalezas"]
F1["Operatividad 100% offline (Local-First)"]
F2["Multiplataforma (Android/Windows)"]
F3["Bajo coste de mantenimiento"]
F4["Base preparada para Verifactu"]
end

    subgraph D["Debilidades"]
      D1["Sin certificación fiscal en fase TFG"]
      D2["Menos funcionalidades que suites SaaS"]
      D3["Ecosistema comercial inicial limitado"]
    end

    subgraph O["Oportunidades"]
      O1["Nueva normativa fiscal (Verifactu)"]
      O2["Digitalización creciente en pymes"]
      O3["Interés en soluciones sin cuotas"]
    end

    subgraph A["Amenazas"]
      A1["Competencia de grandes SaaS"]
      A2["Cambios regulatorios futuros"]
      A3["Dependencia de adopción del mercado"]
    end
end

F --> O
D --> A
F --> A
D --> O
