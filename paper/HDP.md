## Hierarchical Dirichlet Process

### Introduction

狄利克雷过程$\text{DP}(\alpha_0,G_0)$是测度上的测度。它有两个参数，一个*scaling*参数$\alpha_0>0$和一个基概率测度(base probability measure)$G_0$。一个具体的表示从狄利克雷过程中采样的是Sethuranma，
他证明了如果$G\sim \text{DP}(\alpha_0,G_0)$，那么以概率$1$：
$$
G = \sum_{k=1}^\infty \beta_k\delta_{\phi_K}
$$
其中$\phi_k$为服从$G_0$的独立同分布，其中"stick-breaking weights"$\beta_k$依赖于参数$\alpha_0$。

让我们考虑我们的数据被分为一定数量的组(groups)的情况。给定我们在每一个组内进行聚类的问题，我们考虑一系列的随机测度$G_j$，其中$G_j$来自于一个group-specific狄利克雷过程$\text{DP}(\alpha_{0j},G_{0j})$。
为了将这些聚类问题连接起来，我们将group-specific狄利克雷过程链接起来。通过$G_{0j}$，一个自然的方法是分层，
认为$G_j$是从单个狄利克雷过程$\text{DP}(\alpha_0,G_0(\tau))$中抽样得到的，其中$G_0(\tau)$是以随机参数$\tau$为参数的参数分布。
对$\tau$积分引入了DP之间的依赖关系。

但是这样仍然不能解决我们的问题。因为虽然$G_j$都是从$\text{DP}$中抽样得到的，但是每个$G_j$中仍然不会存在相同的元素。我们对此的解决方法也很简单，我们假设$G_0$来自
一个新的狄利克雷过程$\text{DP}(\gamma, H)$，这样$G_0$也为一个离散的分布，我们的问题就解决了。这就是分层狄利克雷过程：
$$
\begin{aligned}
G_0\mid \gamma,H &\sim \text{DP}(\gamma,H)\\
G_j\mid \alpha_0,G_0\mid &\sim \text{DP}(\alpha_0,G_0)
\end{aligned}
$$


